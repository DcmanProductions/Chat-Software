package com.dcmanproductions.vid_eo.mapper;

import org.chris.portmapper.model.Protocol;
import org.chris.portmapper.model.SinglePortMapping;

public class InitializePortMapper extends SinglePortMapping{
	int exPort, inPort;
	Protocol protocol;
	public InitializePortMapper(int exPort, int inPort, Protocol protocol) {
		SinglePortMapping spm = new SinglePortMapping(protocol,inPort,exPort);
	}
	
}
