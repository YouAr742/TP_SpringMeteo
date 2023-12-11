package tp.ensim.tp3INFO2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressResponse {
    private String type;
    private String version;
    private Feature[] features;

    // Getters and setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Feature[] getFeatures() {
        return features;
    }

    public void setFeatures(Feature[] features) {
        this.features = features;
    }

    public static class Feature {
        private String type;
        private Geometry geometry;
        private Properties properties;

        // Getters and setters

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        public Properties getProperties() {
            return properties;
        }

        public void setProperties(Properties properties) {
            this.properties = properties;
        }
    }

    public static class Geometry {
        private String type;
        private double[] coordinates;

        // Getters and setters

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double[] getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(double[] coordinates) {
            this.coordinates = coordinates;
        }
    }

    public static class Properties {
        private String label;
        private double score;
        private String housenumber;
        private String id;
        private String name;
        private String postcode;
        private String citycode;
        private double x;
        private double y;
        private String city;
        private String context;
        private String type;
        private double importance;
        private String street;

        // Getters and setters

        @JsonProperty("label")
        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        @JsonProperty("score")
        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        @JsonProperty("housenumber")
        public String getHousenumber() {
            return housenumber;
        }

        public void setHousenumber(String housenumber) {
            this.housenumber = housenumber;
        }

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("postcode")
        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        @JsonProperty("citycode")
        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        @JsonProperty("x")
        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        @JsonProperty("y")
        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        @JsonProperty("city")
        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        @JsonProperty("context")
        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("importance")
        public double getImportance() {
            return importance;
        }

        public void setImportance(double importance) {
            this.importance = importance;
        }

        @JsonProperty("street")
        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }
    }
}
