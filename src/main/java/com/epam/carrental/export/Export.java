package com.epam.carrental.export;

/**
 * Factory for exporter format
 */
public class Export {
    public static Exporter export(String format) {
        switch (format) {
            case "pdf":
                return new PDF();
            case "xlsx":
                return new Excel();
            case "csv":
                return new CSV();
            case "print":
                return new Printer();
            default:
                throw new IllegalArgumentException("don't support format " + format);
        }
    }
}
