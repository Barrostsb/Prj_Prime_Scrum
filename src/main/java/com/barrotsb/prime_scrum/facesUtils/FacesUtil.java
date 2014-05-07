package com.barrotsb.prime_scrum.facesUtils;

import javax.faces.event.ActionEvent;

public class FacesUtil {
	public static Object getActionAttribute(ActionEvent event, String name) {
        return event.getComponent().getAttributes().get(name);
    }
}
