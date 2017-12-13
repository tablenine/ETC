appendTo: 'body',
		className: null,
		closeOnWindowScroll: false,
		disableTextInput: false,
		disableTimeRanges: [],
		disableTouchKeyboard: false,
		durationTime: null,
		forceRoundTime: false,
		maxTime: null,
		minTime: null,
		noneOption: false,
		orientation: 'l',
		roundingFunction: function(seconds, settings) {
			if (seconds === null) {
				return null;
			} else if (typeof settings.step !== "number") {
				// TODO: nearest fit irregular steps
				return seconds;
			} else {
				var offset = seconds % (settings.step*60); // step is in minutes

				if (offset >= settings.step*30) {
					// if offset is larger than a half step, round up
					seconds += (settings.step*60) - offset;
				} else {
					// round down
					seconds -= offset;
				}

				return seconds;
			}
		},
		scrollDefault: null,
		selectOnBlur: false,
		show2400: false,
		showDuration: false,
		showOn: ['click', 'focus'],
		showOnFocus: true,
		step: 30,
		stopScrollPropagation: false,
		timeFormat: 'g:ia',
		typeaheadHighlight: true,
		useSelect: false
