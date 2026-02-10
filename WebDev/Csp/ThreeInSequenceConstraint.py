from Constraint import Constraint
from Point import Point


class ThreeInSequenceConstraint (Constraint):

    def __init__ (self, variable: Point, size: int):
        super(type(variable))
        self.variable = variable
        self.size = size
    
    def satisfied (self, assignment) -> bool :
        fir_r = assignment.get(Point(self.variable.x-2, self.variable.y))
        sec_r = assignment.get(Point(self.variable.x-1, self.variable.y))
        thi_r = assignment.get(Point(self.variable.x, self.variable.y))
        for_r = assignment.get(Point(self.variable.x+1, self.variable.y))
        fif_r = assignment.get(Point(self.variable.x+2, self.variable.y))

        if (Point.equals(fir_r, sec_r) and Point.equals(fir_r, thi_r)) or ((Point.equals(sec_r, thi_r)) and (Point.equals(thi_r, for_r))) or ((Point.equals(thi_r, for_r)) and (Point.equals(for_r, fif_r))):
            return False

        fir_r = assignment.get(Point(self.variable.x, self.variable.y-2))
        sec_r = assignment.get(Point(self.variable.x, self.variable.y-1))
        thi_r = assignment.get(Point(self.variable.x, self.variable.y))
        for_r = assignment.get(Point(self.variable.x, self.variable.y+1))
        fif_r = assignment.get(Point(self.variable.x, self.variable.y+2))

        if (Point.equals(fir_r, sec_r) and Point.equals(fir_r, thi_r)) or ((Point.equals(sec_r, thi_r)) and (Point.equals(thi_r, for_r))) or ((Point.equals(thi_r, for_r)) and (Point.equals(for_r, fif_r))):
            return False
        return True
    

