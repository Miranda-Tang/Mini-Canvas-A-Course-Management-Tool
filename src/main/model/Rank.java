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
    },
    INVALID {
        @Override
        public String toString() {
            return "invalid";
        }
    }
}
