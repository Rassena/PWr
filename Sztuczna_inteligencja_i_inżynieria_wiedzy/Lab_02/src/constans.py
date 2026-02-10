
CENTER_SQUARES = [(3, 3), (3, 4), (4, 3), (4, 4)]
CORNER_SQUARES = [(0, 0), (0, 7), (7, 0), (7, 7)]
EDGE_SQUARES = [(i, 0) for i in range(8)] + \
               [(i, 7) for i in range(8)] + \
               [(0, j) for j in range(8)] + \
               [(7, j) for j in range(8)]

CENTER_VALUE = 10
CORNER_VALUE = 20
EDGE_VALUE = 5
MOBILITY_VALUE = 5
BASE_PIECE_VALUE = 1

PLAYER_1 = 1

PC_1 = 1
MAX_DEPTH_1 = 3

PC_2 = 2
MAX_DEPTH_2 = 4

CONTROL_CENTER_SATISFIED = CENTER_VALUE * 3
CONTROL_CORNERS_SATISFIED = CORNER_VALUE * 3
CONTROL_EDGE_SATISFIED = EDGE_VALUE * 3
MOBILITY_SATISFIED = MOBILITY_VALUE * 10
PIECE_COUNT_SATISFIED = BASE_PIECE_VALUE * 20
