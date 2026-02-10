from Point import Point


class Constraint:
    def __init__(self,variable: Point):
        self.variable = variable

    def satisfied (self, assignment) -> bool:
        return False
    

