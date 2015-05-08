package com.chinasofti.rcloud.common.cloudfoundry.domain;

import java.io.Serializable;

public class CFBean implements Serializable {
	
	private MetadataBean  metadata;
	
	public MetadataBean getMetadata() {
		return metadata;
	}

	public void setMetadata(MetadataBean metadata) {
		this.metadata = metadata;
	}

	public QuotaBean getEntity() {
		return entity;
	}

	public void setEntity(QuotaBean entity) {
		this.entity = entity;
	}

	private QuotaBean entity;

}
