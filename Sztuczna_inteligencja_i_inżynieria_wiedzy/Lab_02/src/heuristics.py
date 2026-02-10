# Control the center of the board
# Control the edges of the board
# Control the corners of the board
# Mobility
# Corner mobility
# Block the opponent's moves
# Flanking
# Piece count
# Threat analysis
# Piece placement
# Disrupt opponent's patterns
# Long-term planning
# Sacrifice for gain
from constans import CENTER_SQUARES, CORNER_SQUARES, EDGE_SQUARES, CENTER_VALUE, CORNER_VALUE, EDGE_VALUE, \
    MOBILITY_VALUE, BASE_PIECE_VALUE, CONTROL_CENTER_SATISFIED, CONTROL_EDGE_SATISFIED, MOBILITY_SATISFIED, \
    PIECE_COUNT_SATISFIED, CONTROL_CORNERS_SATISFIED
from reversi import Reversi, get_opponent


def control_center(game: Reversi, player):
    """ Control the center of the board """
    score = game.get_player_pieces(player) * BASE_PIECE_VALUE
    for square in CENTER_SQUARES:
        if game.board[square[0]][square[1]] == player:
            score += CENTER_VALUE
    return score


def control_edge(game: Reversi, player):
    """ Control the edges of the board """
    score = game.get_player_pieces(player) * BASE_PIECE_VALUE
    for square in EDGE_SQUARES:
        if game.board[square[0]][square[1]] == player:
            score += EDGE_VALUE
    return score


def control_corners(game: Reversi, player):
    """ Control the corners of the board """
    score = game.get_player_pieces(player) * BASE_PIECE_VALUE
    for square in CORNER_SQUARES:
        if game.board[square[0]][square[1]] == player:
            score += CORNER_VALUE
    return score


def mobility(game: Reversi, player):
    """ Mobility """
    score = game.get_player_pieces(player) * BASE_PIECE_VALUE
    score += (len(game.get_valid_moves(player)) - len(game.get_valid_moves(get_opponent(player)))) * MOBILITY_VALUE
    return score


def piece_count(game: Reversi, player):
    """ Piece Count """
    counts = game.get_players_pieces()
    my_pieces = counts[player]
    opponent_pieces = counts[get_opponent(player)]
    score = my_pieces - opponent_pieces
    return score
