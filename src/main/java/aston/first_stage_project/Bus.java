package aston.first_stage_project;

class Bus implements Comparable<Bus>{
    private String number;
    private String model;
    private int run;

    public Bus(BusBuilder builder) {
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
        int compareNumber = this.number.compareTo(o.number);
        if (compareNumber != 0)
            return compareNumber;

        int compareModel = this.model.compareTo(o.model);
        if (compareModel != 0)
            return compareModel;

        return Integer.compare(this.run, o.run);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", run=" + run +
                '}';
    }

    public static class BusBuilder{
        private String number;
        private String model;
        private int run;

        public BusBuilder () {

        }

        public BusBuilder setNumber (String  number) {
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

}

