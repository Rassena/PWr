def print_board(board):
    print("   0 1 2 3 4 5 6 7 ")
    print("  +-+-+-+-+-+-+-+-+")
    for row in range(8):
        print(row, end=" |")
        for col in range(8):
            if board[row][col] == 0:
                print(" ", end="|")
            elif board[row][col] == 1:
                print("X", end="|")
            else:
                print("O", end="|")
        print("\n  +-+-+-+-+-+-+-+-+")


def tie():
    print("It's a tie!")


def no_valid_moves(player):
    print(f"No valid moves for player {player}")


def player_turn(player_number):
    print(f"\nPlayer {player_number}'s turn:")


def valid_moves(valid_moves, player):
    print(f"Valid moves for player {player}: {valid_moves}")


def invalid_move():
    print("Invalid move")


def winner(player):
    print(f"Player {player} wins!")


def count(player_1, player_2):
    print(f" {player_1} : {player_2}")


def player_put(player, row, col):
    print(f"Player {player} put on: ({row};{col})")


def change_heuristic(player, new_heuristic):
    print(f"Player {player} use heuristic: {new_heuristic.__name__}")
