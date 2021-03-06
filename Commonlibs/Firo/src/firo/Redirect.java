/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

/**
 *
 * @author hoaronal
 */
public final class Redirect {

    public enum Status {
        MULTIPLE_CHOICES(300),
        MOVED_PERMANENTLY(301),
        FOUND(302),
        SEE_OTHER(303),
        NOT_MODIFIED(304),
        USE_PROXY(305),
        SWITCH_PROXY(306),
        TEMPORARY_REDIRECT(307),
        PERMANENT_REDIRECT(308);

        private int intValue;

        private Status(int intValue) {
            this.intValue = intValue;
        }

        public int intValue() {
            return intValue;
        }
    }

    static Redirect create(Routable http) {
        return new Redirect(http);
    }

    private Redirect(Routable http) {
        this.http = http;
    }

    private Routable http;

    public void any(String fromPath, String toPath) {
        any(fromPath, toPath, null);
    }

    public void get(String fromPath, String toPath) {
        get(fromPath, toPath, null);
    }

    public void post(String fromPath, String toPath) {
        post(fromPath, toPath, null);
    }
	
    public void put(String fromPath, String toPath) {
        put(fromPath, toPath, null);
    }

    public void delete(String fromPath, String toPath) {
        delete(fromPath, toPath, null);
    }

    public void any(String fromPath, String toPath, Status status) {
        get(fromPath, toPath, status);
        post(fromPath, toPath, status);
        put(fromPath, toPath, status);
        delete(fromPath, toPath, status);
    }

    public void get(String fromPath, String toPath, Status status) {
        http.get(fromPath, redirectRoute(toPath, status));
    }

    public void post(String fromPath, String toPath, Status status) {
        http.post(fromPath, redirectRoute(toPath, status));
    }

    public void put(String fromPath, String toPath, Status status) {
        http.put(fromPath, redirectRoute(toPath, status));
    }

    public void delete(String fromPath, String toPath, Status status) {
        http.delete(fromPath, redirectRoute(toPath, status));
    }

    private static Route redirectRoute(String toPath, Status status) {
        return (req, res) -> {
            if (status != null) {
                res.redirect(toPath, status.intValue());
            } else {
                res.redirect(toPath);
            }
            return null;
        };
    }

}
