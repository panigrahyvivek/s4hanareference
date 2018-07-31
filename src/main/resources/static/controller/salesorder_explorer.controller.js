sap.ui.define([
	"sap/ui/core/mvc/Controller"
], function (Controller) {
	"use strict";

	return Controller.extend("com.sap.s4hana.sdk.demo.ui.s4hana_explorer.controller.salesorder_explorer", {

		onInit: function(){
			var salesOrderModel = new sap.ui.model.json.JSONModel("/s4cloud/salesorders", null, false);
			this.getView().setModel(salesOrderModel);
		}
	});
});