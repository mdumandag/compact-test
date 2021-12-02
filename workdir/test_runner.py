import argparse
import subprocess
import time
from string import Template

OBJECT_KINDS = [
    "COMPACT",
    "PORTABLE",
    "IDENTIFIED",
    "BYTE_ARRAY",
]

OBJECT_SIZES = [
    "LARGE",
]

READ_TEST_CLASS_NAME = "org.example.tests.ReadTest"
WRITE_TEST_CLASS_NAME = "org.example.tests.WriteTest"
QUERY_TEST_CLASS_NAME = "org.example.tests.QueryTest"
OLD_QUERY_ENGINE_TEST_CLASS_NAME = "org.example.tests.OldQueryEngineTest"


def write_test_properties(text):
    with open("test.properties", "w") as f:
        f.write(text)


parser = argparse.ArgumentParser()
parser.add_argument("--write", dest="write", action="store_true")
parser.add_argument("--read", dest="read", action="store_true")
parser.add_argument("--query", dest="query", action="store_true")
parser.add_argument("--old-engine-query", dest="old_engine_query", action="store_true")

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

old_engine_query_throughput_test_template = Template(
    """class=$clazz
objectKind=$kind
objectSize=$size
    """
)

old_engine_query_latency_test_template = Template(
    """class=$clazz
objectKind=$kind
objectSize=$size
ratePerSecond=10
    """
)

for kind in OBJECT_KINDS:
    for size in OBJECT_SIZES:
        test_properties_throughput = throughput_test_template.substitute(
            clazz=WRITE_TEST_CLASS_NAME,
            kind=kind,
            size=size,
        )

        write_test_properties(test_properties_throughput)
        subprocess.run(["bash", "run.sh", f"write_{kind}_{size}_throughput"])

        time.sleep(30)
