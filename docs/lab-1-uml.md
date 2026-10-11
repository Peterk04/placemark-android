# Lab 1 data model

The model, store interface and memory implementation follow the Lab 1 architecture. `x` and `y` use the coordinate defaults from its model example. The console runner is a set of top-level functions in `main/Main.kt`.

```mermaid
classDiagram
    class PlacemarkModel {
        +Long id
        +String title
        +String description
        +Double x
        +Double y
    }
    class PlacemarkStore {
        <<interface>>
        +findAll() List~PlacemarkModel~
        +create(placemark)
        +update(placemark) Boolean
        +delete(id) Boolean
        +findOne(id) PlacemarkModel?
    }
    class PlacemarkMemStore {
        -ArrayList~PlacemarkModel~ placemarks
        -AtomicLong lastId
    }
    PlacemarkStore <|.. PlacemarkMemStore
    PlacemarkMemStore o-- PlacemarkModel : stores
```
