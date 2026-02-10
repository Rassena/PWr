import copy
import random

import constants


def tournament(solutions: list, scores: list, size=constants.TOURNAMENT_SIZE):
    """
    :param solutions:
    :param scores:
    :param size:
    :return:
    """
    round_solutions = copy.deepcopy(solutions)
    round_scores = copy.deepcopy(scores)
    combat_solutions = []
    combat_scores = []
    draws = random.sample(range(len(round_solutions)), size)
    for draw in draws:
        combat_solutions.append(round_solutions[draw])
        combat_scores.append(round_scores[draw])
    winner_solution, winner_score = battle(combat_solutions, combat_scores)
    return winner_solution, winner_score


def battle(solutions: list, scores: list):
    """

    :param solutions:
    :param scores:
    :return:
    """
    best = scores[0]
    best_position = 0
    for i in range(len(solutions)):
        if scores[i] < best:
            best = scores[i]
            best_position = i
    return solutions[best_position], scores[best_position]
