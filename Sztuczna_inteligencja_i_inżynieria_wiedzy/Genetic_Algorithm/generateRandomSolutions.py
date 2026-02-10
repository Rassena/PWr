import random


def fix_solution(solution: list, grid_height: int, grid_width: int):
    fixed_solution = solution
    for i in range(len(fixed_solution) - 1):
        for j in range(i+1, len(fixed_solution)):
            if fixed_solution[i] == fixed_solution[j]:
                #print(fixed_solution[i], fixed_solution[j])
                fixed_solution[j] = (random.randint(0, grid_width - 1), random.randint(0, grid_height - 1))
                #print("New ", fixed_solution)
    return fixed_solution


def is_correct_solution(solution: [int]) -> bool:
    """
    check if that solution is possible (2 machines on same place in grid)

    :param solution: list of machine's position in grid
    :return:
    """
    correct = True
    for j in range(len(solution)):
        for i in range(len(solution)):
            if i != j:
                if solution[j] == solution[i]:
                    #print(solution[j], solution[i])
                    correct = False
    return correct


def generate_random_solutions(amount_of_machines: int, grid_height: int, grid_width: int,
                              amount_of_solutions: int) -> [[list]]:
    """
    Generate random correct solution

    :param amount_of_machines: amount of machines placed in grid
    :param grid_height: height of tested grid area
    :param grid_width: width of tested grid area
    :param amount_of_solutions: amount of random solutions to generate
    :return: list of machine position in solution
    """

    solutions = []
    solution_number = 0
    while solution_number < amount_of_solutions:
        solution = []
        for machine_number in range(amount_of_machines):
            solution.append((random.randint(0, grid_width - 1), random.randint(0, grid_height - 1)))
        correct = False
        while not correct:
            solution = fix_solution(solution, grid_height, grid_width)
            correct = is_correct_solution(solution)
            #print(correct)
        solutions.append(solution)
        solution_number += 1
        # else:
        # print("Wrong Solution:", solution)

    return solutions
