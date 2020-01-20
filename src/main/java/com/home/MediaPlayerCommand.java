package com.home;

import lombok.Getter;

public enum MediaPlayerCommand {
	VOLUME_UP("volume_up"), VOLUME_DOWN("volume_down"), SELECT_SOURCE("select_source");

	@Getter
	private String value;

	MediaPlayerCommand(String value) {
		this.value = value;
	}
}
