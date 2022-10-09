package model;

public enum Rank {
    EXCELLENT {
        @Override
        public String toString() {
            return "excellent";
        }
    },
    GOOD {
        @Override
        public String toString() {
            return "good";
        }
    },
    ADEQUATE {
        @Override
        public String toString() {
            return "adequate";
        }
    },
    INSUFFICIENT {
        @Override
        public String toString() {
            return "insufficient";
        }
    },
    UNACCEPTABLE {
        @Override
        public String toString() {
            return "unacceptable";
        }
    };
}
