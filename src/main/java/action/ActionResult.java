package action;

public class ActionResult {
    private String view;
    private boolean redirect;

    public ActionResult(String view, boolean redirect) {
        this.view = view;
        this.redirect = redirect;
    }

    public ActionResult(String view) {
        this(view, false);
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }
}
