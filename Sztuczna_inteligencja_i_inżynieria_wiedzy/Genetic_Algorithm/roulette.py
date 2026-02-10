from random import random


def roulette(solutions: list, scores: list):
    """
    roulette selecting method for new generation

    :param solutions:
    :param scores:
    :return:
    """
    new_generation_solutions = list()
    new_generation_scores = list()
    for i in range(len(solutions)):
        winner_solution, winner_score = roulette_one(solutions, scores)
        new_generation_solutions.append(winner_solution)
        new_generation_scores.append(winner_score)

    return new_generation_solutions, new_generation_scores


def roulette_one(solutions: list, scores: list):
    """
   roulette selecting method for new generation

   :param solutions:
   :param scores:
   :return:
   """
    winner_solution = list()
    winner_score = int
    sum_of_scores = 0
    solutions_thresholds = list()
    for i in range(len(solutions)):
        sum_of_scores += scores[i]
    for i in range(len(solutions)):
        previous = 0
        if i != 0:
            previous = solutions_thresholds[i - 1]
        solutions_thresholds.append(previous + scores[i] / sum_of_scores)
    draw = random()
    # print(draw)
    selected = 0
    for j in range(len(solutions_thresholds)):
        # print(solutions_thresholds[j])
        if draw < solutions_thresholds[j]:
            selected = j - 1
            break
    winner_solution = solutions[selected]
    winner_score = scores[selected]

    return winner_solution, winner_score
