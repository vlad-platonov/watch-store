package com.watch.store.controller;

import static com.watch.store.util.MessageConstants.ENTER_WATCH_TYPE;
import static com.watch.store.util.MessageConstants.INVALID_INPUT_WATCH_TYPE;

import com.watch.store.controller.factory.WatchFactory;
import com.watch.store.service.WatchService;
import com.watch.store.view.InputHandler;
import com.watch.store.view.View;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class that contains methods for menu options.
 */
public class MenuOptionController {

    private final WatchService service;
    private final View view;
    private final InputHandler input;
    private final List<WatchFactory> factories;

    public MenuOptionController(WatchService service, View view, InputHandler input,
        List<WatchFactory> factories) {
        this.service = service;
        this.view = view;
        this.input = input;
        this.factories = factories;
    }

    public void addWatch() {
        String title = factories.stream().map(WatchFactory::getWatchType)
            .collect(Collectors.joining(", ", ENTER_WATCH_TYPE + " (", "):\n"));
        view.printMessage(title);
        choiceWatchFactory(input.readInput());
    }

    public void showAllWatches() {
        service.getAllWatches().stream().map(Objects::toString).forEach(view::printMessage);
    }

    public void showTotalPrice() {
        view.printMessage(service.getTotalPrice().toString());
    }

    public void sortWatchesByColor() {
        service.sortByColor().stream().map(Objects::toString).forEach(view::printMessage);
    }

    public void sortWatchesByPrice() {
        service.sortByPrice().stream().map(Objects::toString).forEach(view::printMessage);
    }

    public void sortWatchesByArrivalDate() {
        service.sortByArrivalDate().stream().map(Objects::toString).forEach(view::printMessage);
    }

    private void choiceWatchFactory(String watchType) {
        factories.stream()
            .filter(factory -> factory.getWatchType().equalsIgnoreCase(watchType))
            .findFirst()
            .ifPresentOrElse(factory ->
                    service.addWatch(factory.createWatch()),
                () -> view.printMessage(INVALID_INPUT_WATCH_TYPE));
    }

}
