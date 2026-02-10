import datetime

import data as data

from ReadData import read_from_json_file, save_to_text
from calculateScore import calculate_solutions_score, get_best_solutions, get_worst_solutions
from crossover import crossover
from generateRandomSolutions import generate_random_solutions
from mutation import mutation
from roulette import roulette, roulette_one
from tournament import tournament
import constants as constants


def test():
    # [cost_path,flow_path, number_of_machines,grid_height,grid_width,solutions_to_generate,number_of_best_solutions]
    easy_problem = [constants.EASY_COST_PATH, constants.EASY_FLOW_PATH, 9, 3, 3, 100, 1]
    flat_problem = [constants.FLAT_COST_PATH, constants.FLAT_FLOW_PATH, 12, 1, 12, 100, 1]
    hard_problem = [constants.HARD_COST_PATH, constants.HARD_FLOW_PATH, 24, 5, 6, 100, 1]
    tournament_size = 4

    problems = [easy_problem, flat_problem, hard_problem]

    for problem in problems:
        print(f"\n\n=================={problem[0]}=============================")

        # get costs and flows
        costs = read_from_json_file(problem[0])
        flows = read_from_json_file(problem[1])
        number_of_machines = problem[2]
        grid_height = problem[3]
        grid_width = problem[4]
        solutions_to_generate = problem[5]
        number_of_best_solutions = problem[6]

        print("\n\tRandom starting solutions\n")
        # generate random solutions
        random_solutions = generate_random_solutions(number_of_machines, grid_height, grid_width, solutions_to_generate)
        print(random_solutions)

        # calculate scores for solutions
        scores = calculate_solutions_score(random_solutions, costs, flows)
        print(scores)

        print("\n\tBest Solution \n")
        # get best solutions with scores
        best_solution, best_score = get_best_solutions(random_solutions, scores, 1)
        print(best_solution)
        print(best_score)
        # print(best_solutions[0][1])

        print("\n\tRoulette Result\n")
        # roulette
        drawn_solution, drawn_score = roulette_one(random_solutions, scores)
        print(drawn_solution)
        print(drawn_score)

        print("\n\tTournament Result\n")
        tournament_solution, tournament_score = tournament(random_solutions, scores, tournament_size)
        print(tournament_solution)
        print(tournament_score)

        print("\n\tMutation Result\n")
        mutation_solution = mutation(random_solutions)
        mutation_scores = calculate_solutions_score(mutation_solution, costs, flows)
        print(mutation_solution)
        print(mutation_scores)

        print("\n\tcrossover Result\n")
        crossover_solution = crossover(random_solutions, scores, grid_height, grid_width)
        print(crossover_solution)


if __name__ == "__main__":
    tournament_size = 8
    generations = 100
    population_size = 100
    constants.CROSSOVER_PROBABILITY = 20
    constants.MUTATION_PROBABILITY = 3
    constants.SELECTION_TYPE = 0  # 0 -tournament 1 - roulettte

    easy_problem = [constants.EASY_COST_PATH, constants.EASY_FLOW_PATH, 9, 3, 3, population_size, 1]
    flat_problem = [constants.FLAT_COST_PATH, constants.FLAT_FLOW_PATH, 12, 1, 12, population_size, 1]
    hard_problem = [constants.HARD_COST_PATH, constants.HARD_FLOW_PATH, 24, 5, 6, population_size, 1]

    problems = [easy_problem, flat_problem, hard_problem]
    problem = problems[0]
    costs = read_from_json_file(problem[0])
    flows = read_from_json_file(problem[1])
    number_of_machines = problem[2]
    grid_height = problem[3]
    grid_width = problem[4]
    # solutions_to_generate = problem[5]
    number_of_best_solutions = problem[6]

    solutions = generate_random_solutions(number_of_machines, grid_height, grid_width, population_size)
    scores = calculate_solutions_score(solutions, costs, flows)
    prbl = "easy"
    now = datetime.datetime.now()
    time = now.strftime("%H_%M_%S")
    filename = f'{datetime.date.today()}_{time}_{prbl}_g{generations}_p{population_size}_c{constants.CROSSOVER_PROBABILITY}_m{constants.MUTATION_PROBABILITY}_t{tournament_size}_s{constants.SELECTION_TYPE}'

    test_cases = 10
    for generation in range(generations):
        tests_array = []
        for test in range(test_cases):
            selected = []
            for solution in range(population_size):
                winner = None
                if constants.SELECTION_TYPE == 0:
                    winner, _ = tournament(solutions, scores, tournament_size)
                else:
                    winner, _ = roulette_one(solutions, scores)
                selected.append(winner)
            scores = calculate_solutions_score(selected, costs, flows)
            crossover_solution = crossover(selected, scores, grid_height, grid_width)
            mutation_solution = mutation(crossover_solution)
            mutation_scores = calculate_solutions_score(mutation_solution, costs, flows)
            tests_array.append((mutation_solution, mutation_scores))
        if generation % 10 == -1:
            print(generation, mutation_solution)
            print(generation, mutation_scores)
            best_solution, best_score = get_best_solutions(mutation_solution, mutation_scores, 1)
            print(best_solution)
            print(best_score)
            print(filename)

        # get best
        _, best = get_best_solutions(mutation_solution, mutation_scores, 1)
        for sol in tests_array:
            _, best_score = get_best_solutions(mutation_solution, mutation_scores, 1)
            if (best_score < best):
                best = best_score

        _, worst = get_worst_solutions(mutation_solution, mutation_scores, 1)
        for sol in tests_array:
            _, worst_score = get_worst_solutions(mutation_solution, mutation_scores, 1)
            if (worst_score > worst):
                worst = worst_score
        avg = 0
        for tst in tests_array:
            #print(tst)
            tscores = tst[1]
            for scr in tscores:
                #print(scr)
                avg+=scr
        #print(test_cases*population_size)
        #print(avg)
        avg = avg/(test_cases*population_size)
        to_save = f'{generation};{best[0]};{worst[0]};{avg}'
        print(to_save)
        save_to_text(filename,to_save)
    pass
