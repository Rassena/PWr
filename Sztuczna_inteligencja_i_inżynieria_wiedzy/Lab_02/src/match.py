import heuristics
from constans import PLAYER_1, PC_2, MAX_DEPTH_1, MAX_DEPTH_2
from interface import print_board
from reversi import Reversi, get_opponent
import interface
from utils import read_move_player, do_best_move, heuristic_change


def player_vs_player():
    game = Reversi()
    current_player = PLAYER_1
    while not game.game_over():
        valid_moves = game.get_valid_moves(current_player)
        if not valid_moves:
            interface.no_valid_moves(current_player)
            current_player = get_opponent(current_player)
            continue
        interface.player_turn(current_player)
        print_board(game.board)
        interface.count(*game.get_players_pieces()[1:])
        interface.valid_moves(valid_moves, current_player)
        row, col = read_move_player()
        while (row, col) not in valid_moves:
            interface.invalid_move()
            row, col = read_move_player()
        game.make_move(row, col, current_player)
        current_player = get_opponent(current_player)
    print_board(game.board)
    winner = game.get_winner()
    if winner == 0:
        interface.tie()
    else:
        interface.winner(winner)
        interface.count(*game.get_players_pieces()[1:])


def player_vs_pc(evaluate=heuristics.piece_count, pc=PC_2, pc_minmax=True, dynamic=False):
    game = Reversi()
    current_player = PLAYER_1
    while not game.game_over():
        valid_moves = game.get_valid_moves(current_player)
        if not valid_moves:
            interface.no_valid_moves(current_player)
            current_player = get_opponent(current_player)
            continue
        interface.player_turn(current_player)
        print_board(game.board)
        interface.count(*game.get_players_pieces()[1:])
        interface.valid_moves(valid_moves, current_player)
        if current_player != pc:
            row, col = read_move_player()
            while (row, col) not in valid_moves:
                interface.invalid_move()
                row, col = read_move_player()
        else:
            if dynamic:
                evaluate = heuristic_change(game, current_player, evaluate)
                interface.change_heuristic(current_player, evaluate)
            row, col = do_best_move(game, MAX_DEPTH_1, current_player, evaluate, pc_minmax)
        game.make_move(row, col, current_player)
        interface.player_put(current_player, row, col)
        current_player = get_opponent(current_player)
    print_board(game.board)
    winner = game.get_winner()
    if winner == 0:
        interface.tie()
    else:
        interface.winner(winner)
        interface.count(*game.get_players_pieces()[1:])


def pc_vs_pc(pc1_evaluate, pc2_evaluate, pc1_minmax=True, pc2_minmax=True, pc1_dynamic=False, pc2_dynamic=False):
    game = Reversi()
    current_player = PLAYER_1
    while not game.game_over():
        valid_moves = game.get_valid_moves(current_player)
        if not valid_moves:
            interface.no_valid_moves(current_player)
            current_player = get_opponent(current_player)
            continue
        interface.player_turn(current_player)
        print_board(game.board)
        interface.count(*game.get_players_pieces()[1:])
        interface.valid_moves(valid_moves, current_player)
        if current_player == 1:
            if pc1_dynamic:
                pc1_evaluate = heuristic_change(game, current_player, pc1_evaluate)
                interface.change_heuristic(current_player, pc1_evaluate)
            row, col = do_best_move(game, MAX_DEPTH_1, current_player, pc1_evaluate, pc1_minmax)
        else:
            if pc1_dynamic:
                pc2_evaluate = heuristic_change(game, current_player, pc2_evaluate)
                interface.change_heuristic(current_player, pc2_evaluate)
            row, col = do_best_move(game, MAX_DEPTH_2, current_player, pc2_evaluate, pc2_minmax)
        game.make_move(row, col, current_player)
        interface.player_put(current_player, row, col)
        current_player = get_opponent(current_player)
    print_board(game.board)
    winner = game.get_winner()
    if winner == 0:
        interface.tie()
    else:
        interface.winner(winner)
        interface.count(*game.get_players_pieces()[1:])
