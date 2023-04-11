public class CurrentConditions {
    public interface Display {
        public void update(float temperature, float humidity, float pressure);

        public void display();
    }

    public class currentConditions implements Display {
        private float temperature;
        private float humidity;
        private float pressure;
        private WeatherStation weatherStation;

        public currentConditions(WeatherStation weatherStation) {
            this.weatherStation = weatherStation;
            this.weatherStation.registerObserver(this);
        }

        public void update(float temperature, float humidity, float pressure) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.pressure = pressure;
            display();
        }

        public void display() {
            System.out.printf("Current conditions: %.1fF degrees, %.1f%% humidity, and %.1fin pressure\n", temperature, humidity, pressure);
        }
    }

    public class StatisticsDisplay implements Display {
        private float temperatureMin = Float.MAX_VALUE;
        private float temperatureMax = Float.MIN_VALUE;
        private float tempRunningTotal = 0.0f;
        private int numReadings = 0;

        public void update(float temperature, float humidity, float pressure) {
            temperatureMin = Math.min(temperatureMin, temperature);
            temperatureMax = Math.max(temperatureMax, temperature);
            tempRunningTotal += temperature;
            numReadings++;
            display();
        }

        public void display() {
            float temperatureAverage = tempRunningTotal / numReadings;
            System.out.printf("Avg/Max/Min temperature: %.1fF/%.1fF/%.1fF\n", temperatureAverage, temperatureMax, temperatureMin);
        }
    }

    public class ForecastDisplay implements Display {
        private float lastPressure;
        private float currentPressure = 29.92f;

        public void update(float temperature, float humidity, float pressure) {
            lastPressure = currentPressure;
            currentPressure = pressure;
            display();
        }

        @Override
        public void display() {

        }
    }
}



