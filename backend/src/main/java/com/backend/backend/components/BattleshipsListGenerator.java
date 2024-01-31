package com.backend.backend.components;

import com.backend.backend.components.interfaces.ListGenerator;
import com.backend.backend.models.Ship;
import com.backend.backend.models.ShipType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BattleshipsListGenerator implements ListGenerator<Ship> {

    public List<Ship> generate() {
        List<ShipType> shipTypes = createShipTypesList();
        List<Ship> ships = ConvertShipTypesToShipObjects(shipTypes);
        shuffleList(ships);
        return ships;
    }
    private static List<Ship> ConvertShipTypesToShipObjects(List<ShipType> shipTypes) {
        return shipTypes.stream().map(shipType -> {
            Ship ship = new Ship();
            ship.setHitPoints(getShipHitPointsByType(shipType));
            return ship;
        }).collect(Collectors.toList());
    }

    private static List<ShipType> createShipTypesList() {
        return Arrays.asList(
                ShipType.CARRIER, ShipType.CARRIER, ShipType.CARRIER,
                ShipType.BATTLESHIP, ShipType.BATTLESHIP, ShipType.BATTLESHIP,
                ShipType.CRUISER, ShipType.CRUISER,
                ShipType.SUBMARINE,
                ShipType.DESTROYER
        );
    }

    private static int getShipHitPointsByType(ShipType shipType) {
        return switch (shipType) {
            case CARRIER -> 1;
            case BATTLESHIP -> 2;
            case CRUISER -> 3;
            case SUBMARINE -> 4;
            case DESTROYER -> 5;
        };
    }

    private static<T> void shuffleList(List<T> list) {
        Collections.shuffle(list);
    }
}
