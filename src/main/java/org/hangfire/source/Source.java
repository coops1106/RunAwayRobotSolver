package org.hangfire.source;

public interface Source {
    String retrieveData(int level);
    String retrieveData();
}
