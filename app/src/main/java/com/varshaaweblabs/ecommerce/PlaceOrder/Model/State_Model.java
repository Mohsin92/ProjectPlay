package com.varshaaweblabs.ecommerce.PlaceOrder.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 14/10/17.
 */

public class State_Model implements Serializable {

    @SerializedName("states")
    @Expose
    private List<State_List_Resp> states = null;

    public List<State_List_Resp> getStates() {
        return states;
    }

    public void setStates(List<State_List_Resp> states) {
        this.states = states;
    }
}
