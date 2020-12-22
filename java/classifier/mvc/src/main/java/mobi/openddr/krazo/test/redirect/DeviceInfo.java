package mobi.openddr.krazo.test.redirect;

import jakarta.mvc.RedirectScoped;

import java.io.Serializable;

/**
 * Holds device information.
 *
 * @author Ivar Grimstad
 */
@RedirectScoped
public class DeviceInfo implements Serializable {

    private static final long serialVersionUID = 347652475167L;

    private boolean isMobile;
    private boolean isTablet;

    public DeviceInfo() {
    }

    public void setTablet(boolean tablet) {
        isTablet = tablet;
        isMobile = !tablet;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public boolean isTablet() {
        return isTablet;
    }
}
