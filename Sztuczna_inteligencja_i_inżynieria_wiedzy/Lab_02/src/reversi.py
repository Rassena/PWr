def get_opponent(player):
    return 3 - player


class Reversi:
    def __init__(self):
        self.board = [[0] * 8 for _ in range(8)]
        self.board[3][3] = self.board[4][4] = 1
        self.board[3][4] = self.board[4][3] = 2

    def get_valid_moves(self, player):
        moves = []
        for row in range(8):
            for col in range(8):
                if self.is_valid_move(row, col, player):
                    moves.append((row, col))
        return moves

    def is_valid_move(self, row, col, player):
        if self.board[row][col] != 0:
            return False
        for d_row in range(-1, 2):
            for d_col in range(-1, 2):
                if d_row == 0 and d_col == 0:
                    continue
                if self.is_valid_direction(row, col, d_row, d_col, player):
                    return True
        return False

    def is_valid_direction(self, row, col, d_row, d_col, player):
        opponent = get_opponent(player)
        r, c = row + d_row, col + d_col
        if r < 0 or r >= 8 or c < 0 or c >= 8 or self.board[r][c] != opponent:
            return False
        while 0 <= r < 8 and 0 <= c < 8:
            if self.board[r][c] == 0:
                return False
            if self.board[r][c] == player:
                return True
            r, c = r + d_row, c + d_col
        return False

    def make_move(self, row, col, player):
        self.board[row][col] = player
        for d_row in range(-1, 2):
            for d_col in range(-1, 2):
                if d_row == 0 and d_col == 0:
                    continue
                if self.is_valid_direction(row, col, d_row, d_col, player):
                    self.flip_direction(row, col, d_row, d_col, player)

    def flip_direction(self, row, col, d_row, d_col, player):
        r, c = row + d_row, col + d_col
        while self.board[r][c] != player:
            self.board[r][c] = player
            r, c = r + d_row, c + d_col

    def get_players_pieces(self):
        counts = [0, 0, 0]
        for row in range(8):
            for col in range(8):
                counts[self.board[row][col]] += 1
        return counts

    def get_player_pieces(self, player):
        counts = self.get_players_pieces()
        return counts[player]

    def get_winner(self):
        counts = self.get_players_pieces()
        if counts[1] > counts[2]:
            return 1
        elif counts[2] > counts[1]:
            return 2
        else:
            return 0

    def game_over(self) -> bool:
        player_1 = len(self.get_valid_moves(1))
        player_2 = len(self.get_valid_moves(2))
        return (player_1 + player_2) == 0
