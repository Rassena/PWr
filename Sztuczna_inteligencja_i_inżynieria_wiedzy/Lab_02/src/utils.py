import copy

from constans import MOBILITY_SATISFIED, CONTROL_CORNERS_SATISFIED, CONTROL_CENTER_SATISFIED, CONTROL_EDGE_SATISFIED, \
    PIECE_COUNT_SATISFIED
from reversi import Reversi, get_opponent
import heuristics

possible_heuristics = [heuristics.piece_count, heuristics.control_edge, heuristics.control_center,
                       heuristics.control_corners, heuristics.mobility]
heuristics_satisfaction = [PIECE_COUNT_SATISFIED, CONTROL_EDGE_SATISFIED, CONTROL_CENTER_SATISFIED,
                           CONTROL_CORNERS_SATISFIED, MOBILITY_SATISFIED]



def read_move_player():
    while True:
        try:
            row, col = map(int, input("Enter row and column: ").split())
        except ValueError:
            print("invalid value!")
            continue
        return row, col


def evaluate(game: Reversi, player, heuristic=heuristics.piece_count):
    player_score = heuristic(game, player) + heuristics.piece_count(game, player)
    opponent_score = heuristics.piece_count(game, get_opponent(player))
    return player_score - opponent_score


def heuristic_change(game: Reversi, player, heuristic):
    if heuristic_satisfied(game, player, heuristic):
        heuristic_values = [evaluate(game, player, possible_heuristics[i]) - heuristics_satisfaction[i] for i in range(len(possible_heuristics))]
        next_heuristic = 0
        for k in range(len(heuristic_values) - 1):
            if heuristic_values[next_heuristic] > heuristic_values[k + 1]:
                next_heuristic = k + 1
        print(f"changed to : {possible_heuristics[next_heuristic].__name__}")
        return possible_heuristics[next_heuristic]
    return heuristic


def heuristic_satisfied(game: Reversi, player, heuristic):
    for i in range(len(possible_heuristics)):
        if heuristic == possible_heuristics[i]:
            return evaluate(game, player, heuristic) > heuristics_satisfaction[i]



def do_best_move(game_state: Reversi, depth, player, evaluate, minmax=True):
    moves = game_state.get_valid_moves(player)
    best_move = moves[0]
    best_value = float('-inf')
    for move in moves[1:]:
        new_game = copy.deepcopy(game_state)
        new_game.make_move(move[0], move[1], player)
        if minmax:
            value = minimax(new_game, depth - 1, player, False, evaluate)
        else:
            value = alphabeta(new_game, depth - 1, player, True, float('-inf'), float('inf'), evaluate)
        if value > best_value:
            best_move = move
            best_value = value
    return best_move[0], best_move[1]


def minimax(game_state: Reversi, depth, player, max_player, evaluate):
    if depth == 0 or game_state.game_over():
        return evaluate(game_state, player)

    if max_player:
        value = float('-inf')
        for move in game_state.get_valid_moves(player):
            new_game_state = copy.deepcopy(game_state)
            new_game_state.make_move(move[0], move[1], player)
            value = max(value, minimax(new_game_state, depth - 1, player, False, evaluate))
        return value
    else:
        value = float('inf')
        for move in game_state.get_valid_moves(get_opponent(player)):
            new_game_state = copy.deepcopy(game_state)
            new_game_state.make_move(move[0], move[1], get_opponent(player))
            value = min(value, minimax(new_game_state, depth - 1, player, True, evaluate))
        return value


def alphabeta(game_state: Reversi, depth, player, max_player, alpha, beta, evaluate):
    if depth == 0 or game_state.game_over():
        return evaluate(game_state, player)

    if max_player:
        value = float('-inf')
        for move in game_state.get_valid_moves(player):
            new_game_state = copy.deepcopy(game_state)
            new_game_state.make_move(move[0], move[1], player)
            value = max(value, alphabeta(new_game_state, depth - 1, player, False, alpha, beta, evaluate))
            alpha = max(alpha, value)
            if beta <= alpha:
                break
        return value
    else:
        value = float('inf')
        for move in game_state.get_valid_moves(get_opponent(player)):
            new_game_state = copy.deepcopy(game_state)
            new_game_state.make_move(move[0], move[1], get_opponent(player))
            value = min(value, alphabeta(new_game_state, depth - 1, player, True, alpha, beta, evaluate))
            beta = min(beta, value)
            if beta <= alpha:
                break
        return value
