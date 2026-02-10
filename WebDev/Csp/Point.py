class Point:

    def __init__(self, x: int, y: int):
        self.y = y
        self.x = x

    def getY(self) -> int:
        return self.y

    def getX(self) -> int:
        return self.x;

    def toString(self) -> str:
        return F"Point x= {self.x}, y= {self.y}"

    def equals(self, point) -> bool:
        if self and point:
            if self.getX() == point.getX() and self.getY() == point.getY():
                return True
        return False

    def hashCode(self) -> int:
        hash = 7;
        hash = 71 * hash + self.x;
        hash = 71 * hash + self.y;
        return hash
