package aston.first_stage_project;

import java.util.Objects;

public class Bus implements Comparable<Bus> {
    private final String number;
    private final String model;
    private final int run;

    private Bus(BusBuilder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.run = builder.run;
    }

    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public int getRun() {
        return run;
    }

    @Override
    public int compareTo(Bus o) {
        int comparable = compareNullable(this.number, o.number);
        if (comparable != 0) {
            return comparable;
        }
        comparable = compareNullable(this.model, o.model);
        if (comparable != 0) {
            return comparable;
        }
        return compareNullable(this.run, o.run);
    }

    private <T extends Comparable<T>> int compareNullable(T a, T b) {
        if (a == null && b == null) {
            return 0;
        }
        if (a == null) {
            return -1;
        }
        if (b == null) {
            return 1;
        }
        return a.compareTo(b);
    }

    @Override
    public String toString() {
        return "Bus{" + "number='" + number + '\'' + ", model='" + model + '\'' + ", run=" + run + '}';
    }

    public static class BusBuilder {
        private String number;
        private String model;
        private int run;

        public BusBuilder() {}

        public BusBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public BusBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder setRun(int run) {
            this.run = run;
            return this;
        }

        public Bus build() {
            return new Bus(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return getRun() == bus.getRun() && Objects.equals(getNumber(), bus.getNumber()) && Objects.equals(getModel(), bus.getModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getModel(), getRun());
    }
}

