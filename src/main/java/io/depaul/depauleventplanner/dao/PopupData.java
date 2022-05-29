package io.depaul.depauleventplanner.dao;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class PopupData {
    private final String buttonLabel;
    private final String popupPrompt;
    private final String callbackUrlConfirmation;
}
