sap.ui.define([
	"sap/ui/core/mvc/Controller"
], function (Controller) {
	"use strict";

	return Controller.extend("com.sap.s4hana.sdk.demo.ui.s4hana_explorer.controller.costcenter_explorer", {

		onInit: function(){
			var costCenterModel = new sap.ui.model.json.JSONModel("/s4cloud/costcenters", null, false);
			this.getView().setModel(costCenterModel);
			
		}
	});
});