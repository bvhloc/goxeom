package asia.covisoft.goom.mvp.view;

public interface SettingsSignupView {

    void onConnectionFail();
    void onSignup(int result);
    void onTokenReady(String token);
}
