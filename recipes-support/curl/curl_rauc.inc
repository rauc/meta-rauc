# HTTP/2 allows having multiple parallel (range-) requests in flight on the
# same connection without requiring multiple TCP and TLS handshakes.
# This speeds up adaptive updates a lot, since they work by making many
# range-requests for parts of the update bundle.
PACKAGECONFIG:append:class-target = " nghttp2"
