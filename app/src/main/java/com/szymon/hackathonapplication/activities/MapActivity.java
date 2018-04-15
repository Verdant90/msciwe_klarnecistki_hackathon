package com.szymon.hackathonapplication.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.helpers.map.GpsMarker;
import com.szymon.hackathonapplication.interfaces.MapMVP;
import com.szymon.hackathonapplication.models.challenges.Challenge;
import com.szymon.hackathonapplication.models.challenges.PearTimeChallenge;
import com.szymon.hackathonapplication.models.fruits.Fruit;
import com.szymon.hackathonapplication.models.fruits.FruitsDao;
import com.szymon.hackathonapplication.models.shop.BasketVersionIconMapper;
import com.szymon.hackathonapplication.presenters.MapActivityPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mbanje.kurt.fabbutton.FabButton;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.szymon.hackathonapplication.helpers.SystemServiceManager.requestFineLocationPermission;

public class MapActivity extends FragmentActivity implements
        LocationListener, OnMapReadyCallback, MapMVP.View {

    private static GoogleMap mMap;
    private static LatLng location;
    private MapMVP.Presenter presenter;
    private Challenge currentChallenge;
    private CountDownTimer challengeTimerCountDown;
    private boolean mapModeNormal = true;
    private static final float MIN_ZOOM_PREFERENCE = 12f;
    private static final float MIN_ZOOM_PREFERENCE_3D_MODE = 16f;

    @BindView(R.id.sky)
    RelativeLayout skyLayout;
    @BindView(R.id.map_gradient)
    View mapGradientView;
    @BindView(R.id.btn_map_mode)
    FabButton toggleStylesButton;
    @BindView(R.id.text_challenge_timer)
    TextView challengeTimer;
    @BindView(R.id.layout_challenge_panel)
    LinearLayout challengeLayout;
    @BindView(R.id.image_challenge_icon)
    ImageView challengeIcon;
    @BindView(R.id.text_challenge_title)
    TextView challengeTitle;
    @BindView(R.id.text_challenge_current_score)
    TextView challengeCurrentProgressTextView;
    @BindView(R.id.btn_challenges)
    FabButton challengesButton;
    @BindView(R.id.button_shop)
    ImageView shopButton;
    private int challengeCount = 0;
    private boolean challengeMode;

    @OnClick(R.id.btn_map_mode)
    public void switchMapMode() {
        if (mapModeNormal) {
            switchToMapMode3d();
        } else {
            switchToMapModeNormal();
        }
        mapModeNormal = !mapModeNormal;
    }

    private void switchToMapModeNormal() {
        skyLayout.setVisibility(GONE);
        mapGradientView.setVisibility(GONE);
        toggleStylesButton.setIcon(R.drawable.ic_map_3d, R.drawable.ic_map_3d);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(mMap.getCameraPosition().target)
                .zoom(mMap.getCameraPosition().zoom)
                .bearing(0)
                .tilt(0)
                .build();
        mMap.setMinZoomPreference(MIN_ZOOM_PREFERENCE);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void switchToMapMode3d() {
        skyLayout.setVisibility(VISIBLE);
        mapGradientView.setVisibility(VISIBLE);
        toggleStylesButton.setIcon(R.drawable.ic_map_normal, R.drawable.ic_map_normal);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(mMap.getCameraPosition().target)
                .zoom(mMap.getCameraPosition().zoom)
                .tilt(67.5f)
                .bearing(mMap.getCameraPosition().bearing)
                .build();
        mMap.setMinZoomPreference(MIN_ZOOM_PREFERENCE_3D_MODE);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private GpsMarker gpsMarker;

    @OnClick(R.id.btn_current_location)
    public void goToCurrentLocation() {
        final LatLng position = gpsMarker.getPosition();
        if (position == null) return;

        final CameraPosition cameraPosition = CameraPosition.builder()
                .zoom(MIN_ZOOM_PREFERENCE_3D_MODE)
                .target(position)
                .build();

        final CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        mMap.animateCamera(cameraUpdate);
    }

    @OnClick(R.id.btn_challenges)
    public void goToChallengeActivity() {
        Intent intent = new Intent(MapActivity.this, ChallengeActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        presenter = new MapActivityPresenter(this);

        setShopButtonIcon();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Challenge result = data.getParcelableExtra("result");
                startChallengeMode(result);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Try again later!", Toast.LENGTH_SHORT);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setShopButtonIcon();
    }

    private void setShopButtonIcon() {
        final Integer basketVersion = AppPreferences.getBasketVersion();
        shopButton.setImageResource(BasketVersionIconMapper.toDrawableIcon(basketVersion));
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng gdanskLatLng = new LatLng(54.35, 18.6);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(gdanskLatLng)
                .zoom(16)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                this, R.raw.map_style));
        presenter.loadFruits();

        createGpsMarker(gdanskLatLng);
        createLocationUpdates();
        startChallengeMode(new PearTimeChallenge());
    }

    private void createGpsMarker(final LatLng latLng) {
        this.gpsMarker = new GpsMarker(mMap, latLng);
    }

    private void createLocationUpdates() {
        final LocationManager locationManager = getLocationManagerService();
        if (locationManager == null) return;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestFineLocationPermission(this);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 5, this);
    }

    private LocationManager getLocationManagerService() {
        return (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void showGeneratedFruits(final List<Fruit> fruits) {
        for (Fruit fruit : fruits) {
            fruit.drawOnMap(mMap);
        }
        FruitsDao.addFruits(fruits);
    }

    public void updateCurrentChallenge(final List<Fruit> fruitsEaten) {
        if (challengeMode) {
            challengeCount = challengeCount + fruitsEatenForCullentChallenge(fruitsEaten);
            if (challengeCount == currentChallenge.howManyToCollect) {
                endChallengeMode(true);
            } else {
                challengeCurrentProgressTextView.setText(challengeCount + "/" + currentChallenge.howManyToCollect);
            }
        }
    }

    private int fruitsEatenForCullentChallenge(final List<Fruit> fruitsEaten) {
        int counter = 0;
        for (final Fruit fruit : fruitsEaten) {
            if (currentChallenge.fruitTypes.contains(fruit.getType())) {
                counter++;
            }
        }
        return counter;
    }

    @OnClick(R.id.button_shop)
    public void goToShopButton() {
        startActivity(new Intent(this, ShopActivity.class));
    }

    public static LatLng currentLocation() {
        return location;
    }

    public static void addFruitsToMap(final List<Fruit> fruits) {
        for (final Fruit fruit : fruits) {
            fruit.drawOnMap(mMap);
        }
        FruitsDao.addFruits(fruits);
    }

    public void startTimer(final int minutes) {
        final int millis = minutes * 60 * 1000;
        challengeTimerCountDown = new CountDownTimer(millis, 1000) {

            public void onTick(final long millisUntilFinished) {
                int secs = (int) (millisUntilFinished / 1000);
                int mins = secs / 60;
                secs = secs % 60;
                updateTimer(getString(R.string.flight_time_format, mins, String.format("%02d", secs)));
            }

            public void onFinish() {
                endChallengeMode(false);
            }
        };
        challengeTimerCountDown.start();
    }

    private void updateTimer(final String timeLeft) {
        challengeTimer.setText(timeLeft);
    }

    public void startChallengeMode(final Challenge challenge) {
        challengeMode = true;
        currentChallenge = challenge;
        challengesButton.setClickable(false);
        challengesButton.setEnabled(false);
        challengesButton.setAlpha(0.5f);
        setChallengeLayoutStyle(challenge);
        startTimer(challenge.timeInMinutes);
    }

    private void setChallengeLayoutStyle(final Challenge challenge) {
        challengeLayout.setVisibility(VISIBLE);
        challengeIcon.setImageDrawable(challenge.getFruitIcon());
        challengeTitle.setText(challenge.title);
        challengeCurrentProgressTextView.setText("0/" + challenge.howManyToCollect);
    }

    public void endChallengeMode(boolean success) {
        challengeMode = false;
        currentChallenge = null;
        challengesButton.setClickable(true);
        challengesButton.setEnabled(true);
        challengesButton.setAlpha(1f);
        challengeTimerCountDown.cancel();
        challengeLayout.setVisibility(GONE);
        challengeCount = 0;
        if (success) {
            //TODO!!!
            //showSuccessMessage();
            //receiveReward();
            Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show();
        } else {
            //showFailureMessage();
            Toast.makeText(this, "FAIL", Toast.LENGTH_LONG).show();
        }
    }

    private Location previousLocation;
    private Long previousLocationTime;

    @Override
    public void onLocationChanged(final Location location) {
        final LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
        checkDistance(location, System.currentTimeMillis());
        previousLocation = location;
        previousLocationTime = System.currentTimeMillis();
        if (AppPreferences.isApplicationBlocked()) {
            gpsMarker.hideRange();
            gpsMarker.changePosition(currentLocation);
            return;
        } else {
            if (gpsMarker != null) {
                gpsMarker.showRange();
            }
        }

        gpsMarker.changePosition(currentLocation);
        MapActivity.location = currentLocation;

        final List<Fruit> fruitsRemoved = FruitsDao.removeFruitsInRange(location);
        updateCurrentChallenge(fruitsRemoved);
    }

    private void checkDistance(final Location location, final long timeStamp) {
        if (previousLocation != null && previousLocationTime != null) {
            //TODO cheatSafety (time) speed check 100 m / 9.56
            if (speedIsOk(location, previousLocation, timeStamp, previousLocationTime)) {
                AppPreferences.increaseDistance(location.distanceTo(previousLocation));
                Log.i(this.getClass().getName(), "ok");
                AppPreferences.unlockApplication();
            } else {
                Log.i(this.getClass().getName(), "tooo fast");
                Toast.makeText(this, "You are mooving too fast! Slow Down", Toast.LENGTH_LONG).show();
                AppPreferences.blockApplication();
            }
        }
    }

    final static double MAX_HUMAN_SPEED = 100f / 9.56f;

    private boolean speedIsOk(final Location currentLocation, final Location previousLocation, final Long currentLocationTime, final Long previousLocationTime) {
        float distance = currentLocation.distanceTo(previousLocation);
        double timeMilis = currentLocationTime - previousLocationTime;
        double timeSec = timeMilis / 1000;
        Log.i(" speed", "time : " + timeSec);
        Log.i(" speed", "speed : " + distance / timeSec);
        //Log.d("speed : ", Double.valueOf(currentSpeed).toString());
        Double v = distance / timeSec;
        return MAX_HUMAN_SPEED > v;
    }

    @Override
    public void onStatusChanged(final String s, final int i, final Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(final String s) {

    }

    @Override
    public void onProviderDisabled(final String s) {

    }
}
