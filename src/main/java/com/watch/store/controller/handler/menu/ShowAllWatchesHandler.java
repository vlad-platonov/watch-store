package com.watch.store.controller.handler.menu;

import static com.watch.store.util.MessageConstants.MENU_OPTION_SHOW_ALL_WATCHES;

import com.watch.store.controller.MenuOptionController;


/**
 * A menu handler that shows all watches.
 */
public class ShowAllWatchesHandler implements MenuHandler {

    private final MenuOptionController controller;

    public ShowAllWatchesHandler(MenuOptionController controller) {
        this.controller = controller;
    }

    @Override
    public void handle() {
        controller.showAllWatches();
    }

    @Override
    public String getMenuDescription() {
        return MENU_OPTION_SHOW_ALL_WATCHES;
    }

}
