import constants as constans


def calculate_solutions_score(solutions: [[int]], costs: dict, flows: dict) -> [float]:
    """
    Calculate scores for every solution in list

    :param solutions:
    :param costs:
    :param flows:
    :return: list of solutions scores
    """

    scores = []
    for solution in solutions:
        scores.append(calculate_solution_score(solution, costs, flows))

    return scores


def get_best_solutions(solutions: [], scores: [], amount: int = 1) -> [([int], float)]:
    """
    Get list of best :amount: solutions with their score

    :param solutions:
    :param scores:
    :param amount:
    :return: list of tuples (solution, score)
    """
    best_solutions = []
    best_scores = []

    n = len(scores)
    for i in range(n - 1):
        for j in range(0, n - i - 1):
            if scores[j] > scores[j + 1]:
                scores[j], scores[j + 1] = scores[j + 1], scores[j]
                solutions[j], solutions[j + 1] = solutions[j + 1], solutions[j]

    for i in range(amount):
        best_solutions.append(solutions[i])
        best_scores.append(scores[i])
    return best_solutions, best_scores


def get_worst_solutions(solutions: [], scores: [], amount: int = 1) -> [([int], float)]:
    """
    Get list of worst :amount: solutions with their score

    :param solutions:
    :param scores:
    :param amount:
    :return: list of tuples (solution, score)
    """
    worst_solutions = []
    worst_scores = []

    n = len(scores)
    for i in range(n - 1):
        for j in range(0, n - i - 1):
            if scores[j] < scores[j + 1]:
                scores[j], scores[j + 1] = scores[j + 1], scores[j]
                solutions[j], solutions[j + 1] = solutions[j + 1], solutions[j]

    for i in range(amount):
        worst_solutions.append(solutions[i])
        worst_scores.append(scores[i])
    return worst_solutions, worst_scores


def calculate_solution_score(solution: [int], costs: dict, flows: dict) -> float:
    """
    Calculate solution score

    :param solution:
    :param costs:
    :param flows:
    :return: solution score
    """

    score = 0

    for flow in flows:
        # print(flow.get(SOURCE), flow.get(DEST), flow.get(AMOUNT))
        source = flow.get(constans.SOURCE)
        destination = flow.get(constans.DEST)
        for cost in costs:
            pass
            # print(cost.get(SOURCE), cost.get(DEST), cost.get(COST))
            if source == cost.get(constans.SOURCE) and destination == cost.get(constans.DEST):
                distance = abs(solution[source][0] - solution[destination][0]) + abs(
                    solution[source][1] - solution[destination][1])
                score += distance * cost.get(constans.COST) * flow.get(constans.AMOUNT)

    return score
