package org.hangfire.problem;

import org.hangfire.source.Source;

public class ProblemFactory {

    private Source source;

    public ProblemFactory(final Source source) {
        this.source = source;
    }

    public Problem fetchProblemForLevel(final int level) {
		return new Problem(this.source.retrieveData(level));
	}

    public Problem fetchProblem() {
		return new Problem(this.source.retrieveData());
	}
}
