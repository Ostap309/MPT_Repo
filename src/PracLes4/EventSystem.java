package PracLes4;

import java.util.ArrayList;
import java.util.List;

public class EventSystem {

    public interface EventHandler<T> {
        void handle(T event);
    }

    public interface EventFilter<T> {
        boolean accept(T event);
    }

    public static class Event {
        private final String type;
        private final String data;
        private final long timestamp;

        public Event(String type, String data) {
            this.type = type;
            this.data = data;
            this.timestamp = System.currentTimeMillis();
        }

        public String getType() {
            return type;
        }

        public String getData() {
            return data;
        }

        public long getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return "Event{type='" + type + "', data='" + data + "', timestamp=" + timestamp + "}";
        }
    }

    private final List<EventHandler<Event>> handlers = new ArrayList<>();
    private EventFilter<Event> currentFilter;

    public void setFilter(EventFilter<Event> filter) {
        this.currentFilter = filter;
    }

    public void addHandler(EventHandler<Event> handler) {
        handlers.add(handler);
    }

    public void fire(Event event) {
        if (currentFilter != null && !currentFilter.accept(event)) {
            System.out.println("Событие отфильтровано: " + event);
            return;
        }
        for (EventHandler<Event> handler : handlers) {
            handler.handle(event);
        }
    }

    public static void main(String[] args) {
        EventSystem eventSystem = new EventSystem();

        eventSystem.setFilter(event -> "ERROR".equals(event.getType()));

        eventSystem.addHandler(event -> System.out.println("Обработано событие: " + event));

        eventSystem.fire(new Event("INFO", "Информационное сообщение"));
        eventSystem.fire(new Event("ERROR", "Сообщение об ошибке"));
        eventSystem.fire(new Event("DEBUG", "Отладочное сообщение"));
        eventSystem.fire(new Event("ERROR", "Ещё одна ошибка"));
    }
}
