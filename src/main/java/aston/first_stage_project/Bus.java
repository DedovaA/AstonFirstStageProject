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

        if (this.number == null && o.number == null) {
            return 0;
        }
        if (this.number == null) {
            return -1;
        }
        if (o.number == null) {
            return 1;
        }
        int i = this.number.compareTo(o.number);
        if (i != 0) return i;

        if (this.model == null && o.model == null) {
            return 0;
        }
        if (this.model == null) {
            return -1;
        }
        if (o.model == null) {
            return 1;
        }
        int j = this.model.compareTo(o.model);
        if (j != 0) return j;

        return Integer.compare(this.run, o.run);
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

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            BusBuilder that = (BusBuilder) o;
            return run == that.run && Objects.equals(number, that.number) && Objects.equals(model, that.model);
        }

        @Override
        public int hashCode() {
            return Objects.hash(number, model, run);
        }
    }
}

