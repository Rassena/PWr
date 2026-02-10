import sys

from Point import Point


class Csp:
    staticint = 0

    def __init__(self, variables, domains: dict):
        self.variables = variables
        self.domains = domains
        self.constraints = {}

        for variable in variables:
            self.constraints[variable] = list()
            if self.domains.get(variable) == None:
                raise Exception ("Every variable should have a domain assigned to it.")



    def addConstraint(self, constraint):
        newConstraint = self.constraints.get(constraint.variable)
        newConstraint.append(constraint)
        self.constraints[constraint.variable] = newConstraint

    def consistent(self, variable, assignment)->bool:
        for constraint in self.constraints.get(variable):
            if not constraint.satisfied(assignment):
                return False

        return True

    def backtracking_search(self, assignment):
        result= None
        if len(assignment) == len(self.variables):
            Csp.staticint+=1
            Csp.print(assignment)
            return None

        unassigned = list()
        for j in range(len(self.variables)):
            point = self.variables[j]
            try:
                if assignment[point] is None:
                    unassigned.append(point)
            except KeyError:
                unassigned.append(point)

            if len(unassigned) > 0:
                first = unassigned[0]
                for i in range(len(self.domains.get(first))):
                    tempAssignment = {}
                    tempAssignment[first] = self.domains.get(first)[i]
                    if self.consistent(first, tempAssignment):
                        result = self.backtracking_search(tempAssignment)
                        if result is not None:
                            return result
                return None
        return None


    def print(assignment):
        print("------------------------------------------------------------------------")
        print(assignment.get(Point(0,5)) + " " + assignment.get(Point(1,5))+ " " + assignment.get(Point(2,5)) + " " +assignment.get(Point(3,5)) + " " + assignment.get(Point(4,5))+ " " + assignment.get(Point(5,5)))
        print(assignment.get(Point(0,4)) + " " + assignment.get(Point(1,4))+ " " + assignment.get(Point(2,4)) + " " +assignment.get(Point(3,4)) + " " + assignment.get(Point(4,4))+ " " + assignment.get(Point(5,4)))
        print(assignment.get(Point(0,3)) + " " + assignment.get(Point(1,3))+ " " + assignment.get(Point(2,3)) + " " +assignment.get(Point(3,3)) + " " + assignment.get(Point(4,3))+ " " + assignment.get(Point(5,3)))
        print(assignment.get(Point(0,2)) + " " + assignment.get(Point(1,2))+ " " + assignment.get(Point(2,2)) + " " +assignment.get(Point(3,2)) + " " + assignment.get(Point(4,2))+ " " + assignment.get(Point(5,2)))
        print(assignment.get(Point(0,1)) + " " + assignment.get(Point(1,1))+ " " + assignment.get(Point(2,1)) + " " +assignment.get(Point(3,1)) + " " + assignment.get(Point(4,1))+ " " + assignment.get(Point(5,1)))
        print(assignment.get(Point(0,0)) + " " + assignment.get(Point(1,0))+ " " + assignment.get(Point(2,0)) + " " +assignment.get(Point(3,0)) + " " + assignment.get(Point(4,0))+ " " + assignment.get(Point(5,0)))


