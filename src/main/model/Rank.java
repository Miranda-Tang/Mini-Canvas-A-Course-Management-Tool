package model;

// Represents the rank of a student's project
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
    }, // invalid input, for test coverage only
    INVALID {
        @Override
        public String toString() {
            return "invalid";
        }
    }
}
