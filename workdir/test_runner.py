import argparse
import subprocess
import time
from string import Template

OBJECT_KINDS = [
    "COMPACT",
    "PORTABLE",
    "IDENTIFIED",
]


OBJECT_SIZES = [
    "SMALL",
    "LARGE",
]


READ_TEST_CLASS_NAME = "org.example.tests.ReadTest"
WRITE_TEST_CLASS_NAME = "org.example.tests.WriteTest"
QUERY_TEST_CLASS_NAME = "org.example.tests.QueryTest"


def write_test_properties(text):
    with open("test.properties", "w") as f:
        f.write(text)


parser = argparse.ArgumentParser()
parser.add_argument("--write", dest="write", action="store_true")
parser.add_argument("--read", dest="read", action="store_true")
parser.add_argument("--query", dest="query", action="store_true")

args = parser.parse_args()


throughput_test_template = Template(
    """class=$clazz
objectKind=$kind
objectSize=$size
    """
)

latency_test_template = Template(
    """class=$clazz
objectKind=$kind
objectSize=$size
ratePerSecond=5000
    """
)

query_throughput_test_template = Template(
    """class=$clazz
objectKind=$kind
    """
)

query_latency_test_template = Template(
    """class=$clazz
objectKind=$kind
ratePerSecond=10
    """
)


if args.write:
    for kind in OBJECT_KINDS:
        for size in OBJECT_SIZES:
            test_properties_throughput = throughput_test_template.substitute(
                clazz=WRITE_TEST_CLASS_NAME, kind=kind, size=size
            )

            write_test_properties(test_properties_throughput)
            subprocess.run(["bash", "run.sh", f"write_{kind}_{size}_throughput"])

            time.sleep(30)

            test_properties_latency = latency_test_template.substitute(
                clazz=WRITE_TEST_CLASS_NAME, kind=kind, size=size
            )

            write_test_properties(test_properties_latency)
            subprocess.run(["bash", "run.sh", f"write_{kind}_{size}_latency"])

            time.sleep(30)

if args.read:
    for kind in OBJECT_KINDS:
        for size in OBJECT_SIZES:
            test_properties_throughput = throughput_test_template.substitute(
                clazz=READ_TEST_CLASS_NAME, kind=kind, size=size
            )

            write_test_properties(test_properties_throughput)
            subprocess.run(["bash", "run.sh", f"read_{kind}_{size}_throughput"])

            time.sleep(30)

            test_properties_latency = latency_test_template.substitute(
                clazz=READ_TEST_CLASS_NAME, kind=kind, size=size
            )

            write_test_properties(test_properties_latency)
            subprocess.run(["bash", "run.sh", f"read_{kind}_{size}_latency"])

            time.sleep(30)

if args.query:
    for kind in OBJECT_KINDS:
        test_properties_throughput = query_throughput_test_template.substitute(
            clazz=QUERY_TEST_CLASS_NAME,
            kind=kind,
        )
        write_test_properties(test_properties_throughput)
        subprocess.run(["bash", "run.sh", f"query_{kind}_throughput"])

        time.sleep(30)

        test_properties_latency = query_latency_test_template.substitute(
            clazz=QUERY_TEST_CLASS_NAME, kind=kind
        )

        write_test_properties(test_properties_latency)
        subprocess.run(["bash", "run.sh", f"query_{kind}_latency"])

        time.sleep(30)
