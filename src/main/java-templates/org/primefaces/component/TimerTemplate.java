import javax.faces.context.FacesContext;
import org.primefaces.util.ComponentUtils;

public final static String STYLE_CLASS = "ui-timer ui-widget ui-widget-header ui-corner-all";

        private java.util.Locale calculatedLocale;
        private java.util.TimeZone appropriateTimeZone;

        public java.util.Locale calculateLocale(FacesContext facesContext) {
		if(calculatedLocale == null) {
			Object userLocale = getLocale();
			if(userLocale != null) {
				if(userLocale instanceof String) {
					calculatedLocale = ComponentUtils.toLocale((String) userLocale);
				}
				else if(userLocale instanceof java.util.Locale)
					calculatedLocale = (java.util.Locale) userLocale;
				else
					throw new IllegalArgumentException("Type:" + userLocale.getClass() + " is not a valid locale type for timer:" + this.getClientId(facesContext));
			} else {
				calculatedLocale = facesContext.getViewRoot().getLocale();
			}
		}
		
		return calculatedLocale;
	}
        
        public boolean patternValidaiton(String value) {
        boolean validationValue = false;
        String[] timeList = value.split(":");
        if (timeList[0].equals(value) && value.length() > 0) {
            validationValue = true;
        } else {
            int timeListLength = value.replaceAll(":", "").length();
            if ((value.length() - timeListLength + 1) != timeList.length) {
                validationValue = false;
            } else {
                if (timeList.length <= 4) {
                    validationValue = true;
                } else {
                    validationValue = false;
                }
            }
        }

        return validationValue;
    }