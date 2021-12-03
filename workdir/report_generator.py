import subprocess

OBJECT_KINDS = [
    "COMPACT",
    "PORTABLE",
    "IDENTIFIED",
    "BYTE_ARRAY",
]

OBJECT_SIZES = [
    "SMALL",
    "LARGE",
]

TESTS = [
    "write",
    "read",
    "query",
    "old_engine_query",
]

TEST_TYPES = ["throughput", "latency"]


def generate_report(test_name, sizes, kinds):
    for size in sizes:
        for test_type in TEST_TYPES:
            folders = [
                f"{test_name}_{kind}_{size + '_' if size else ''}{test_type}"
                for kind in kinds
            ]

            subprocess.run(
                [
                    "benchmark-report",
                    "-o",
                    f"reports/{test_name}_{size + '_' if size else ''}{test_type}",
                    *folders,
                ]
            )


generate_report("write", OBJECT_SIZES, OBJECT_KINDS)
generate_report("read", OBJECT_SIZES, OBJECT_KINDS)
generate_report("query", [""], OBJECT_KINDS[:-1])
generate_report("old_engine_query", OBJECT_SIZES, OBJECT_KINDS[:-1])
