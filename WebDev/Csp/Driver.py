import sys

from Csp import Csp
from EvenInRowAndColConstraint import EvenInRowAndColConstraint
from Point import Point
from RowsAndColumnsDifferConstraint import RowsAndColumnsDifferConstraint
from ThreeInSequenceConstraint import ThreeInSequenceConstraint

if __name__ == "__main__":
    sys.setrecursionlimit(2000)
    sixBySix = list()
    six = 6
    mappedToDomainsSBS = {}
    assignmentSBS = {}
    # set up table

    for i in range(six):
        for j in range(six):
            sixBySix.append(Point(i, j))

    # set up domain
    i = 0
    for i in range(len(sixBySix)):
        mappedToDomainsSBS[sixBySix[i]] = list(range(0, 2))

    # set up primary input
    assignmentSBS[Point(0, 0)] = 1
    assignmentSBS[Point(4, 0)] = 0
    assignmentSBS[Point(1, 2)] = 1
    assignmentSBS[Point(2, 2)] = 1
    assignmentSBS[Point(0, 3)] = 0
    assignmentSBS[Point(4, 3)] = 0
    assignmentSBS[Point(2, 4)] = 0
    assignmentSBS[Point(5, 4)] = 1
    assignmentSBS[Point(3, 5)] = 1
    # set up csp
    csp = Csp(sixBySix, mappedToDomainsSBS)
    # set up constraints
    for point in sixBySix:
        csp.addConstraint(ThreeInSequenceConstraint(point, len(sixBySix)));
        csp.addConstraint(EvenInRowAndColConstraint(point, len(sixBySix)));
        csp.addConstraint(RowsAndColumnsDifferConstraint(point, len(sixBySix)));
    # perform csp backtracking
    csp.backtracking_search(assignmentSBS)
    print(f"results found: {Csp.staticint}")
