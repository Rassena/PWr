import math

from Constraint import Constraint
from Point import Point


class EvenInRowAndColConstraint(Constraint):

    def __init__(self, variable, size):
        super(type(variable))
        self.variable = variable
        self.size = size

    def satisfied(self, assignment):
        # checking row
        zCounter = 0
        oCounter = 0
        rowSize = int(math.sqrt(self.size))
        for index in range(rowSize):
            value = assignment.get(Point(index, self.variable.y))
            if value != None and value == 0:
                zCounter += 1
            elif value != None and value == 1:
                oCounter += 1

        if (zCounter > (rowSize / 2) or oCounter > (rowSize / 2)):
            return False

        # checking column
        zCounterC = 0
        oCounterC = 0
        for index in range(rowSize):
            value = assignment.get(Point(self.variable.x, index))
            if value != None:
                if value == 0:
                    zCounterC += 1
                else:
                    oCounterC += 1

        if zCounterC > (rowSize / 2) or oCounterC > (rowSize / 2):
            return False

        return True
