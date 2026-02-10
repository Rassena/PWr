from Constraint import Constraint
from Point import Point


class RowsAndColumnsDifferConstraint(Constraint):
    def __init__(self, variable: Point, size: int):
        super(type(variable))
        self.variable = variable
        self.size = size

    def satisfied(self, assignment):
        # checking rows
        newRow = list()
        newCol = list()
        for index in range(self.size):  # get the row
            newRow.append(assignment.get(Point(index, self.variable.y)))

        for i in range(self.size):
            if i != self.variable.y:
                equal = True
                for index in range(self.size):
                    po = assignment.get(Point(index, self.variable.y))
                    ne = newRow[index]
                    if po is not None and ne is not None and po.intValue() != ne.intValue():
                        equal = False
                        break

        # checking column
        for index in range(self.size):
            key = Point(self.variable.x, index)
            newCol.append(assignment[key])

        for i in range(self.size):  # for each column
            if i != self.variable.x:
                for index in range(self.size):
                    po = assignment.get(Point(self.variable.x, index))
                    ne = newCol.get(index)
                    if po is not None and ne is not None and not po.equals(ne):
                        break
        return True
