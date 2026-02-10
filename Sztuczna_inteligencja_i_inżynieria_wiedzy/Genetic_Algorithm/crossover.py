import copy
import random

from generateRandomSolutions import is_correct_solution
from roulette import roulette_one
import constants as constants
from tournament import tournament


def crossover(solutions: list, scores: list, grid_height: int, grid_width: int):
    """
    take random number of machines put it to other
    after that fix to be correct
    :param solutions:
    :param scores:
    :return:
    """
    new_population = []
    for approach in range(len(solutions)):
        parent_01 = solutions[approach]
        parent_02 = None
        if constants.SELECTION_TYPE == 0:
            parent_02, _ = roulette_one(solutions, scores)
        else:
            parent_02, _ = tournament(solutions, scores, constants.TOURNAMENT_SIZE)
        child_01 = copy.deepcopy(parent_01)
        if random.random()*100 < constants.CROSSOVER_PROBABILITY:
            gens = random.randint(0, len(parent_02))
            random.sample(solutions, gens)
            for gen in range(gens):
                child_01[gen] = parent_02[gen]
            while not is_correct_solution(child_01):
                child_01 = fix_solution(child_01, grid_height, grid_width)
                #print("New Child: ", child_01)
            new_population.append(child_01)
            #print("Parent 1: ", parent_01)
            #print("Parent 2: ", parent_02)
            #print("child: ", child_01)
        else:
            new_population.append(parent_01)
    return new_population


def fix_solution(solution: list, grid_height: int, grid_width: int):
    fixed_solution = solution
    for i in range(len(fixed_solution) - 1):
        for j in range(i + 1, len(fixed_solution)):
            if fixed_solution[i] == fixed_solution[j]:
                # print(fixed_solution[i], fixed_solution[j])
                fixed_solution[j] = (random.randint(0, grid_width - 1), random.randint(0, grid_height - 1))
    return fixed_solution
