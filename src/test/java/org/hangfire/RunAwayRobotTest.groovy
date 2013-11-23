package org.hangfire

import org.hangfire.problem.ProblemFactory
import org.hangfire.source.StubSource
import spock.lang.Specification

class RunAwayRobotTest extends Specification {
    public static ProblemFactory PROBLEM_FACTORY = new ProblemFactory(new StubSource())
}
